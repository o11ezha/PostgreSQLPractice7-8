package com.project.PostgreSQLPractice78.repos;

import com.project.PostgreSQLPractice78.mainEntities.GraphicsCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraphicsCardRepo extends JpaRepository<GraphicsCard, Integer> {
    GraphicsCard findByGraphicsCardCode(String cardCode);
}
