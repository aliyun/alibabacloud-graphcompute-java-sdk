package com.alibaba.maxgraph.compiler.custom;

public final class Text {
    public static RegexKeyPredicate match(String key, String regex) {
        return new RegexKeyPredicate(key, regex);
    }

    public static RegexPredicate match(String regex) {
        return new RegexPredicate(regex);
    }

    public static StringPredicate startsWith(String content) {
        return new StringPredicate(content, MatchType.STARTSWITH);
    }

    public static StringPredicate endsWith(String content) {
        return new StringPredicate(content, MatchType.ENDSWITH);
    }

    public static StringPredicate contains(String content) {
        return new StringPredicate(content, MatchType.CONTAINS);
    }

    public static StringKeyPredicate startsWith(String key, String content) {
        return new StringKeyPredicate(key, content, MatchType.STARTSWITH);
    }

    public static StringKeyPredicate endsWith(String key, String content) {
        return new StringKeyPredicate(key, content, MatchType.ENDSWITH);
    }

    public static StringKeyPredicate contains(String key, String content) {
        return new StringKeyPredicate(key, content, MatchType.CONTAINS);
    }
}
