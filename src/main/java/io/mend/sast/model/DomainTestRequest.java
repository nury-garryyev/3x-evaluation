package io.mend.sast.model;

public class DomainTestRequest {

  private String domainName;

  public DomainTestRequest() {
  }

  public DomainTestRequest(String domainName) {
    this.domainName = domainName;
  }

  public String getDomainName() {
    return domainName;
  }

  public void setDomainName(String domainName) {
    this.domainName = domainName;
  }

  @Override
  public String toString() {
    return "DomainTestRequest{" +
            "domainName='" + domainName + '\'' +
            '}';
  }
}
