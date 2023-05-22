package com.euije.Simple.infrastructure;

import com.euije.Simple.domain.Post;
import com.euije.Simple.domain.Tag;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TagJDBCRepository implements TagRepository {

    @Value("${spring.datasource.url}")
    String url;

    @Value("${spring.datasource.username}")
    String username;

    @Value("${spring.datasource.password}")
    String password;

    @Override
    public List<Tag> findAll() {
        List<Tag> result = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * from tag";
            PreparedStatement statement = conn.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("name");

                result.add(new Tag(id, title));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(result.isEmpty()) Collections.emptyList();

        return result;
    }

    @Override
    public List<Tag> findAllByPostId(int postId) {
        List<Tag> result = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "select tag_id, name\n" +
                    "from tag, post_tag\n" +
                    "where tag.id = post_tag.tag_id\n" +
                    "and post_id = ?;";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, postId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("tag_id");
                String title = resultSet.getString("name");

                result.add(new Tag(id, title));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(result.isEmpty()) Collections.emptyList();

        return result;
    }

    @Override
    public void save(Tag tag) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO tag (name) VALUES (?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, tag.getName());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Integer> findRecentIdsByCount(int count) {
        List<Integer> result = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT id FROM tag ORDER BY tag.id DESC LIMIT ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, count);

            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                result.add(rs.getInt(1));
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(result.isEmpty()) return Collections.emptyList();

        return result;
    }

    @Override
    public int countPostByTagId(int tagId) {
        int result = 0;

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT count(distinct(post_id))\n" +
                    "FROM post_tag\n" +
                    "WHERE tag_id = ?\n";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, tagId);

            ResultSet rs = statement.executeQuery();
            rs.next();
            result = rs.getInt(1);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void saveRelationsWithPostId(int postId, List<Integer> tagIds) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            for(Integer tagId : tagIds) {
                String query = "INSERT INTO post_tag VALUES (?, ?)";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setInt(1, postId);
                statement.setInt(2, tagId);

                statement.executeUpdate();

                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
