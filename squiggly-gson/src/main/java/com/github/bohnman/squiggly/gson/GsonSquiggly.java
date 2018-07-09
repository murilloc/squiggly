package com.github.bohnman.squiggly.gson;

import com.github.bohnman.squiggly.core.BaseSquiggly;
import com.github.bohnman.squiggly.core.context.provider.SimpleSquigglyContextProvider;
import com.github.bohnman.squiggly.core.context.provider.SquigglyContextProvider;
import com.github.bohnman.squiggly.core.function.SquigglyFunction;
import com.github.bohnman.squiggly.core.function.SquigglyFunctions;
import com.github.bohnman.squiggly.gson.function.GsonFunctions;
import com.github.bohnman.squiggly.gson.json.GsonJsonNode;
import com.google.gson.JsonElement;

import java.util.List;

/**
 * Entry point for apply Squiggly to the Gson library.
 */
public class GsonSquiggly extends BaseSquiggly {

    private GsonSquiggly(BaseBuilder builder) {
        super(builder);
    }

    /**
     * Apply the filters to a json element.
     *
     * @param element the json element
     * @param filters the filters
     * @return transformed element
     */
    public JsonElement apply(JsonElement element, String... filters) {
        return apply(new GsonJsonNode(element), filters).getRawNode();
    }

    /**
     * Initialize with default parameters.
     *
     * @return squiggly
     */
    public static GsonSquiggly init() {
        return builder().build();
    }

    /**
     * Initialize with a filter.
     *
     * @param filter the filter
     * @return squiggly
     */
    public static GsonSquiggly init(String filter) {
        return builder(filter).build();
    }

    /**
     * Initialize with a context provider.
     *
     * @param contextProvider context provider
     * @return squigly
     */
    public static GsonSquiggly init(SquigglyContextProvider contextProvider) {
        return builder(contextProvider).build();
    }

    /**
     * Create a builder that configures Squiggly.
     *
     * @return builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Create a builder that configures Squiggly with a static filter.
     *
     * @param filter static filter
     * @return builder
     */
    public static Builder builder(String filter) {
        return builder().context(new SimpleSquigglyContextProvider(filter));
    }

    /**
     * Create a builder that configures Squiggly with a context provider.
     *
     * @param contextProvider context provider
     * @return builder
     */
    public static Builder builder(SquigglyContextProvider contextProvider) {
        return builder().context(contextProvider);
    }

    /**
     * Custom builder class.
     */
    public static class Builder extends BaseBuilder<Builder, GsonSquiggly> {

        @Override
        protected void applyDefaultFunctions(List<SquigglyFunction<?>> functions) {
            super.applyDefaultFunctions(functions);
            functions.addAll(SquigglyFunctions.create(GsonFunctions.class));
        }

        @Override
        protected GsonSquiggly newInstance() {
            return new GsonSquiggly(this);
        }

    }
}
