package com.euije.Simple.infrastructure;

import com.euije.Simple.domain.Post;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PostJDBCRepository implements PostRepository{

    @Value("${spring.datasource.url}")
    String url;

    @Value("${spring.datasource.username}")
    String username;

    @Value("${spring.datasource.password}")
    String password;

    @Override
    public List<Post> findAll() {
        List<Post> result = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM post";
            PreparedStatement statement = conn.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String contents = resultSet.getString("contents");
                String user = resultSet.getString("user");
                LocalDateTime created_at = resultSet.getTimestamp("created_at").toLocalDateTime();
                LocalDateTime edited_at = resultSet.getTimestamp("edited_at").toLocalDateTime();

                result.add(new Post(id, title, contents, user, created_at, edited_at));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(result.isEmpty())
            return Collections.emptyList();

        return result;
    }

    @Override
    public Optional<Post> findById(int id) {
        Optional<Post> result = Optional.empty();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM post WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int postId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String contents = resultSet.getString("contents");
                String user = resultSet.getString("user");
                LocalDateTime created_at = resultSet.getTimestamp("created_at").toLocalDateTime();
                LocalDateTime edited_at = resultSet.getTimestamp("edited_at").toLocalDateTime();

                result = Optional.of(new Post(postId, title, contents, user, created_at, edited_at));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void save(Post post) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO post (title, contents, user, created_at, edited_at) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getContents());
            statement.setString(3, post.getUser());
            statement.setTimestamp(4, Timestamp.valueOf(post.getCreatedAt()));
            statement.setTimestamp(5, Timestamp.valueOf(post.getEditedAt()));

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int findRecentId() {
        int result = 0;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT id FROM post ORDER BY post.id DESC LIMIT 1";
            PreparedStatement statement = conn.prepareStatement(query);

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
    public int countByTagId(int tagId) {
        int result = 0;

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "select count(post_id)\n" +
                    "from post_tag\n" +
                    "where tag_id = ?";
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
    public void update(Post post, Post newPost) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "UPDATE post SET title = ?, contents = ?, user = ? WHERE post.id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, newPost.getTitle() == null ? post.getTitle() : newPost.getTitle());
            statement.setString(2, newPost.getContents() == null ? post.getContents() : newPost.getContents());
            statement.setString(3, newPost.getUser() == null ? post.getUser() : newPost.getUser());
            statement.setInt(4, post.getId());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int postId) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "DELETE FROM post WHERE post.id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, postId);

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
