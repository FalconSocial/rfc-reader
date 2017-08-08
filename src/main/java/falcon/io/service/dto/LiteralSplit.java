package falcon.io.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
public class LiteralSplit extends WordSplit {

    @Builder
    private LiteralSplit(String created, Payload payload) {
        super(created, payload);
        this.type = "LiteralSplit";
    }

    public static class LiteralSplitBuilder extends WordSplitBuilder {
        LiteralSplitBuilder() {
            super();
        }
    }
}