package falcon.io.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by tibor on 2017-08-07.
 */
@Service
public class RFCProvider {

    private final RestTemplate restTemplate;

    public RFCProvider(){
        this.restTemplate = new RestTemplate();
    }

    public String getRFCAsText(String url){
      return restTemplate.getForObject(url, String.class);
    }

}
