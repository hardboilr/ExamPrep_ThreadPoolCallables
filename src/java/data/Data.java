package data;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private static final List<String> URLS = new ArrayList<String>() {
        {
            //Class A
            add("http://ca2-ebski.rhcloud.com/CA2New/");
            add("http://ca2-pernille.rhcloud.com/NYCA2/");
            add("http://ca2javathehutt-smcphbusiness.rhcloud.com/ca2/index.jsp");

            //Class B
            add("https://ca2-ssteinaa.rhcloud.com/CA2/");
            add("https://ca2-cphol24.rhcloud.com/3.semCa.2/");
            add("https://ca2-ksw.rhcloud.com/DeGuleSider/");
            add("http://ca2-ab207.rhcloud.com/CA2/index.html");
            add("http://ca2gruppe8-tocvfan.rhcloud.com/");
            add("https://ca-ichti.rhcloud.com/CA2/");

            //Class COS
            add("https://ca2-9fitteen.rhcloud.com:8443/CA2/");
            add("https://cagroup04-coolnerds.rhcloud.com/CA_v1/index.html");
        }
    };

    public static List<String> getUrls() {
        return URLS;
    }
}
