package springxml.beans;

import springxml.services.IValidationService;

public class ProfileBean {
    private int ProfileId;
    private String Label;
    private String Detail;
    private IValidationService _validationService;

    public ProfileBean() {
        ProfileId = 0;
        Label = "";
        Detail = "";
    }

    public ProfileBean(IValidationService _validationService) {
        ProfileId = 0;
        Label = "";
        Detail = "";
        this._validationService = _validationService;
    }

    public void set_validationService(IValidationService _validationService) {
        this._validationService = _validationService;
    }

    public int getProfileId() {
        return ProfileId;
    }

    public void setProfileId(int profileId) {
        ProfileId = profileId;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public boolean validateDetails() {
        return _validationService.ValidateString(Detail);
    }
}
