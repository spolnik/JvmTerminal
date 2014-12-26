package com.wordpress.nprogramming.terminal.acceptance.support;

import org.assertj.core.util.Collections;
import org.jbehave.core.ConfigurableEmbedder;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromURL;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.List;

import static com.wordpress.nprogramming.terminal.acceptance.support.ClasspathStoryFinder.findFileNamesThatMatch;
import static org.hamcrest.CoreMatchers.*;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;
import static org.junit.Assert.assertThat;

public final class BehaviouralTestEmbedder extends ConfigurableEmbedder {

    public static final String BAD_USE_OF_API_MESSAGE =
            "You are trying to set the steps factory twice ... bad usage";
    private static final Logger LOG =
            LoggerFactory.getLogger(BehaviouralTestEmbedder.class);
    private String storyFilename;
    private InjectableStepsFactory stepsFactory;

    private BehaviouralTestEmbedder() {
    }

    public static BehaviouralTestEmbedder aBehaviouralTestRunner() {
        return new BehaviouralTestEmbedder();
    }

    @Override
    public void run()
            throws Exception {

        List<String> paths = createStoryPaths();
        throwIfEmpty(paths);

        LOG.info("Running [{}] with JBehave stories [{}]",
                this.getClass().getSimpleName(), paths);

        configuredEmbedder().runStoriesAsPaths(paths);
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        assertThat(stepsFactory, is(notNullValue()));
        return stepsFactory;
    }

    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromURL())
                .useParameterConverters(
                        new ParameterConverters()
                                .addConverters(new SandboxDateConverter()))
                .useStoryReporterBuilder(
                        new SandboxStoryReporterBuilder());
    }

    public BehaviouralTestEmbedder withStory(
            String aStoryFilename) {

        storyFilename = aStoryFilename;
        return this;
    }

    public BehaviouralTestEmbedder usingStepsFrom(
            Object... stepsSource) {

        assertThat(BAD_USE_OF_API_MESSAGE, stepsFactory, is(nullValue()));
        stepsFactory =
                new InstanceStepsFactory(configuration(), stepsSource);
        return this;
    }

    private void throwIfEmpty(List<String> paths) {
        if (Collections.isNullOrEmpty(paths)) {
            throw new IllegalStateException(
                    "No story paths found for state machine");
        }
    }

    private List<String> createStoryPaths() {
        return findFileNamesThatMatch(storyFilename);
    }

    static class SandboxDateConverter
            extends ParameterConverters.DateConverter {

        public SandboxDateConverter() {
            super(new SimpleDateFormat("dd-MM-yyyy"));
        }
    }

    static class SandboxStoryReporterBuilder
            extends StoryReporterBuilder {

        public SandboxStoryReporterBuilder() {
            withCodeLocation(
                    codeLocationFromClass(SandboxStoryReporterBuilder.class));
            withDefaultFormats();
            withFormats(HTML, CONSOLE);
            withFailureTrace(true);
        }
    }
}
