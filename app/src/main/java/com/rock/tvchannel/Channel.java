package com.rock.tvchannel;

public class Channel {

    public String name;
    public String logo;
    public String licenseType;
    public String licenseKey;
    public String url;
    public String id;

    public Channel(
      String name,
      String logo,
      String licenseType,
      String licenseKey,
      String url,
      String id
    ) {
      this.name = name;
      this.logo = logo;
      this.licenseType = licenseType;
      this.licenseKey = licenseKey;
      this.url = url;
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public String getLogo() {
      return logo;
    }

    public String getLicenseType() {
      return licenseType;
    }

    public String getLicenseKey() {
      return licenseKey;
    }

    public String getUrl() {
      return url;
    }
    public String id() {
      return id;
    }
  }
