package com.skillmagnet.Recommendation;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface CourseNodeRepository extends Neo4jRepository<CourseNode, Integer> {
    @Query("MATCH (u:User)-[:ENROLLED_IN]->(c:Course)<-[:ENROLLED_IN]-(other:User)-[:ENROLLED_IN]->(otherCourse:Course) " +
            "WHERE u.userId = $userId " +
            "AND NOT (u)-[:ENROLLED_IN]->(otherCourse) " +
            "AND otherCourse <> c " +
            "WITH otherCourse, COUNT(DISTINCT other) AS popularity " +
            "ORDER BY popularity DESC " +
            "RETURN otherCourse, popularity")
    List<CourseNode> getRecommendedCoursesForUser(int userId);
}
