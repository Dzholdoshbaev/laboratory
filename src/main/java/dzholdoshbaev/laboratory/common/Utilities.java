package dzholdoshbaev.laboratory.common;

import jakarta.servlet.http.HttpServletRequest;

public class Utilities {

    private Utilities() {
    }

    public  static  String getSiteUrl(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        return url.replace(request.getServletPath(), "");
    }
}
