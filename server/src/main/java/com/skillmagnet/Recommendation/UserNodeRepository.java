package com.skillmagnet.Recommendation;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface UserNodeRepository extends Neo4jRepository<UserNode, Integer> {
    @Query("MATCH (u:User {userId: $userId}), (c:Course {courseId: $courseId}) " +
            "CREATE (u)-[r:ENROLLED_IN]->(c) " +
            "RETURN r")
    void enrollUserInCourse(int userId, int courseId);

    @Query("MATCH (n) DETACH DELETE n")
    void deleteAllNodesAndRelationships();
}
