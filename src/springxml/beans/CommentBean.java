package springxml.beans;

import springxml.services.IValidationService;

public class CommentBean {
    private int CommentId;
    private char HostTypeCode;
    private int ImageId;
    private String Content;
    private IValidationService _validationService;

    public CommentBean() {
        CommentId = 0;
        HostTypeCode = ' ';
        ImageId = 0;
        Content = "";
    }

    public CommentBean(IValidationService _validationService) {
        CommentId = 0;
        HostTypeCode = ' ';
        ImageId = 0;
        Content = "";
        this._validationService = _validationService;
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

    public boolean validateContent() {
        return _validationService.ValidateString(Content);
    }
}
