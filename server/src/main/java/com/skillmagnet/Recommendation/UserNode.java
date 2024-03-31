package com.skillmagnet.Recommendation;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@AllArgsConstructor
@Node("User")
public class UserNode {

    @Id
    private int userId;

    private String name;
}