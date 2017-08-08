package falcon.io.service.dto;

import lombok.Builder;

public class LiteralSplit extends WordSplit {
    private String type = "LiteralSplit";

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