package io.metagraph.driver.restservice;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class MgitApiRouter extends AbstractApiRouter {

    @Override
    public void api() {
        //mgit route handle
        this.router.get(GRAPH_API_URL_BASE).handler(this::graphs);
        this.router.post(GRAPH_API_URL_BASE).handler(this::create);
        this.router.get(GRAPH_API_URL_BASE + "/:gid").handler(this::open);
        this.router.put(GRAPH_API_URL_BASE + "/:gid").handler(this::update);
        this.router.delete(GRAPH_API_URL_BASE + "/:gid").handler(this::deleteGraph);
        this.router.post(GRAPH_API_URL_BASE + "/:gid/:action").handler(this::actionGraph);
    }

    /**
     * Open or Close Graph
     *
     * @param ctx RoutingContext
     */
    private void actionGraph(RoutingContext ctx) {
    }

    /**
     * Get Graph
     *
     * @param ctx RoutingContext
     */
    private void open(RoutingContext ctx) {
        String graphId = ctx.request().getParam("gid");
    }

    /**
     * Delete Graph
     *
     * @param ctx RoutingContext
     */
    private void deleteGraph(RoutingContext ctx) {
        String graphId = ctx.request().getParam("gid");
    }

    /**
     * Create Graph
     *
     * @param ctx RoutingContext
     */
    private void create(RoutingContext ctx) {
        JsonObject json = ctx.getBodyAsJson();
    }

    /**
     * Update Graph
     *
     * @param ctx RoutingContext
     */
    private void update(RoutingContext ctx) {

    }

    /**
     * @param ctx RoutingContext
     */
    private void graphs(RoutingContext ctx) {
        ctx.response().end("zhaoliang");
    }

    /**
     * merge graph config
     */
    private JsonObject graphConfig(JsonObject json) {
        JsonObject defaultConfig = vertx.getOrCreateContext().config()
                .getJsonObject("default_graph_config").copy();
        if (json == null || json.getJsonObject("graph_config") == null) {
            return defaultConfig;
        }
        return defaultConfig.mergeIn(json.getJsonObject("graph_config"));
    }

}
