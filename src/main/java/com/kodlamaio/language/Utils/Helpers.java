package com.kodlamaio.language.Utils;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public final class Helpers
{
    private static final Pattern NONLATIN = Pattern.compile("[^\\w\\s]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    public  static  String slug(String value)
    {
        String slug = value.trim();
        slug = slug.toLowerCase();
        slug = Normalizer.normalize(slug, Normalizer.Form.NFD);
        slug = WHITESPACE.matcher(slug).replaceAll("-");
        return slug;
    }
}
