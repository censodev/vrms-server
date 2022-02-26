package io.github.censodev.vrms.vrmsserver.utils;

import lombok.SneakyThrows;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class I18nUtil {
    private static final Locale DEFAULT_LOCALE = new Locale(I18nUtil.Lang.VI);
    private static Locale mLocale = DEFAULT_LOCALE;

    public static void setLocale(String lang) {
        mLocale = Optional.ofNullable(lang)
                .map(String::toLowerCase)
                .map(Locale::forLanguageTag)
                .orElse(DEFAULT_LOCALE);
    }

    @SneakyThrows
    public static String get(String key, Locale... locale) {
        var lcl = locale != null && locale.length > 0 ? locale[0] : mLocale;
        var bundle = ResourceBundle
                .getBundle("i18n/language", lcl);
        return bundle.getString(key);
    }

    public static class Lang {
        public static final String VI = "vi";
        public static final String EN = "en";
    }
}
