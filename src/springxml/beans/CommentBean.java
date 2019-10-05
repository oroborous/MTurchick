package springxml.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import springxml.services.IValidationService;

@Component
public class CommentBean implements IValidatedBean {
    private int CommentId;
    private char HostTypeCode;
    private int ImageId;
    private String Content;
    private IValidationService _validationService;

    @Autowired

    public CommentBean() {
    }

    public CommentBean(@Qualifier("commentValidationService") IValidationService _validationService) {
        this._validationService = _validationService;
        System.out.println("THIS SUCKS");
    }


    public void set_validationService(IValidationService _validationService) {
        this._validationService = _validationService;
    }

    public int getCommentId() {
        return CommentId;
    }

    public void setCommentId(int commentId) {
        CommentId = commentId;
    }

    public char getHostTypeCode() {
        return HostTypeCode;
    }

    public void setHostTypeCode(char hostTypeCode) {
        HostTypeCode = hostTypeCode;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getValidationRules() {
        return _validationService.GiveValidationExplanation();
    }

    public boolean validateContent() {
        return _validationService.ValidateString(Content);
    }
}
