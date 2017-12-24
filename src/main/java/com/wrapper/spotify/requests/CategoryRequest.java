package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Category;

import java.io.IOException;

public class CategoryRequest extends AbstractRequest {

  private CategoryRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Category get() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return new Category.JsonUtil().createModelObject(getJson());
  }

  public SettableFuture<Category> getAsync() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return getAsync(new Category.JsonUtil().createModelObject(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {
    public Builder forCategory(String categoryId) {
      assert (categoryId != null);
      return setPath(String.format("/v1/browse/categories/%s", categoryId));
    }

    /**
     * Required. A valid access token from the Spotify Accounts service
     */
    public Builder accessToken(String accessToken) {
      return setHeaderParameter("Authorization", "Bearer " + accessToken);
    }

    /**
     * Optional. A country: an ISO 3166-1 alpha-2 country code. Provide this parameter if you want to narrow the list of returned categories to those relevant to a particular country. If omitted, the returned items will be globally relevant.
     */
    public Builder country(String country) {
      assert (country != null);
      return setParameter("country", country);
    }

    /**
     * Optional. The desired language, consisting of an ISO 639 language code and an ISO 3166-1 alpha-2 country
     * code, joined by an underscore. For example: es_MX, meaning "Spanish (Mexico)". Provide this parameter if
     * you want the category metadata returned in a particular language.
     * <p>
     * Note that, if locale is not supplied, or if the specified language is not available, all strings will be
     * returned in the Spotify default language (American English).
     * <p>
     * The locale parameter, combined with the country parameter, may give odd results if not carefully matched.
     * For example country=SE&locale=de_DE will return a list of categories relevant to Sweden but as German
     * language strings.
     */
    public Builder locale(String locale) {
      assert (locale != null);
      return setParameter("locale", locale);
    }

    @Override
    public CategoryRequest build() {
      return new CategoryRequest(this);
    }
  }
}
