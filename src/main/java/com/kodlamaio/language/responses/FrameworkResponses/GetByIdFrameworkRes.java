package com.kodlamaio.language.responses.FrameworkResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetByIdFrameworkRes
{
    private String name;
    private String languageName;
}
