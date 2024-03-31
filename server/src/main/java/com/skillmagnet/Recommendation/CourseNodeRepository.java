package com.skillmagnet.Recommendation;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CourseNodeRepository extends Neo4jRepository<CourseNode, Integer> {
}
