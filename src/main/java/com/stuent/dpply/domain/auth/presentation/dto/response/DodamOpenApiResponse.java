package com.stuent.dpply.domain.auth.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DodamOpenApiResponse {

  private final int status;
  private final String message;
  private final DodamInfoData data;

  public DodamOpenApiResponse(int status, String message, DodamInfoData data) {
    this.status = status;
    this.message = message;
    this.data = data;
  }

  @Getter
  @Builder
  @AllArgsConstructor
  public static class DodamInfoData {

    private final String uniqueId;
    private final int grade;
    private final int room;
    private final int number;
    private final String name;
    private final String email;
    private final String profileImage;
    private final int accessLevel;

    public DodamInfoData(DodamInfoData data) {
      this.uniqueId = data.getUniqueId();
      this.grade = data.getGrade();
      this.room = data.getRoom();
      this.number = data.getNumber();
      this.name = data.getName();
      this.email = data.getEmail();
      this.profileImage = data.getProfileImage();
      this.accessLevel = data.getAccessLevel();
    }
  }
}
