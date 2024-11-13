package org.example.ebankify.repository;

import org.example.ebankify.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserManagementRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);

}
