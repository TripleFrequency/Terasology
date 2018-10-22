package org.terasology.version;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TerasologyVersionTest {
    private TerasologyVersion sut;
    private static final Logger logger = LoggerFactory.getLogger(TerasologyVersion.class);
    private static final String VERSION_INFO_FILE = "versionInfo.properties";

    @Before
    public void setup() {
        sut = new TerasologyVersion();
    }

    @Test
    public void ifVersionExists_whenGetHumanVersion_thenCheckHumanVersion() {
        assert(sut.getHumanVersion().toUpperCase().equals(sut.getHumanVersion()));
    }

    @Test
    public void ifVersionExists_whenGetProperty_thenCheckProperty() {
        final Properties properties = new Properties();
        try (InputStream inStream = this.getClass().getResourceAsStream(VERSION_INFO_FILE)) {
            if (inStream != null) {
                properties.load(inStream);
            }
        } catch (final IOException e) {
            logger.error("Loading {} failed", VERSION_INFO_FILE, e);
        }
        assert(sut.getBuildNumber().equals(properties.getProperty("buildNumber", "")));
        assert(sut.getBuildId().equals(properties.getProperty("buildId", "")));
        assert(sut.getBuildTag().equals(properties.getProperty("buildTag", "")));
        assert(sut.getBuildUrl().equals(properties.getProperty("buildUrl", "")));
        assert(sut.getGitBranch().equals(properties.getProperty("gitBranch", "")));
        assert(sut.getGitCommit().equals(properties.getProperty("gitCommit", "")));
        assert(sut.getDateTime().equals(properties.getProperty("dateTime", "")));
        assert(sut.getDisplayVersion().equals(properties.getProperty("DISPLAY_VERSION", "")));
        assert(sut.getengineVersion().equals(properties.getProperty("ENGINE_VERSION", "")));
    }
}
