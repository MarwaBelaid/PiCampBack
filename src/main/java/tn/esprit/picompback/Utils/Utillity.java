package tn.esprit.picompback.Utils;

import javax.servlet.http.HttpServletRequest;

public class Utillity {

    public  static String getSiteURL (HttpServletRequest Request)
    {
        String siteURL = Request.getRequestURL().toString();
        return siteURL.replace(Request.getServletPath(),"");
    }
}
