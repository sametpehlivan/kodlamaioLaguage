package com.kodlamaio.language.responses.FrameworkResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrameworkRes implements Serializable
{
    private String name;
}
