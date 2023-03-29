package com.kodlamaio.language.requests.LanguageRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrameworkReq implements Serializable
{
    private String name;
}
