package com.opengov.prd.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ServiceException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = -150990057576870879L;

  private String code = "";
  private String priority = "";
  private String name = "";
  private String message = "";
  private String type = "";
  private HttpStatus httpStatus = HttpStatus.OK;

  public ServiceException() {
    super();
  }
  
  @Override
  public String getMessage() {
    
    StringBuilder builder = new StringBuilder(50);
    
    builder.append("code=").append(code)
      .append(", priority=").append(priority)
      .append(", type=").append(type)
      .append(", name=").append(name)
      .append(", message=").append(message)
      .append(", httpStatus=").append(httpStatus);

    return builder.toString();
  }
  
  public String getErrorMessage() {
    return this.message;
  }

}