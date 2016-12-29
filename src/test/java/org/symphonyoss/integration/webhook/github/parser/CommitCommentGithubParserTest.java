/**
 * Copyright 2016-2017 Symphony Integrations - Symphony LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.symphonyoss.integration.webhook.github.parser;

import static java.util.Collections.EMPTY_MAP;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;

import org.symphonyoss.integration.webhook.github.CommonGithubTest;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

/**
 * Unit tests for {@link CommitCommentGithubParser}
 *
 * Created by Milton Quilzini on 20/09/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommitCommentGithubParserTest extends CommonGithubTest {
  @Mock
  private GithubParserUtils utils;

  @InjectMocks
  private CommitCommentGithubParser commitCommentGithubParser = new CommitCommentGithubParser();

  @Test
  public void testCommitCommentParse() throws IOException, GithubParserException {
    // files
    JsonNode commitCommentNode = getJsonFile("payload_xgithubevent_commit_comment_created.json");
    String expectedMessage = getExpectedMessageML(
        "payload_xgithubevent_commit_comment_created_expected_message.xml");
    // mocks
    JsonNode publicUserInfo = getJsonFile("payload_github_public_info_baxterthehacker.json");
    doReturn(publicUserInfo).when(utils).doGetJsonApi(anyString());

    // call
    String result = commitCommentGithubParser.parse(EMPTY_MAP, commitCommentNode);
    result = "<messageML>" + result + "</messageML>";
    assertEquals(expectedMessage, result);
  }
}