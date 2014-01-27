package com.tinkerpop.gremlin.oltp.map;

import com.tinkerpop.gremlin.Path;
import com.tinkerpop.gremlin.Pipeline;
import com.tinkerpop.gremlin.util.FunctionRing;

import java.util.function.Function;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class PathPipe<S> extends MapPipe<S, Path> {

    public FunctionRing functionRing;

    public PathPipe(final Pipeline pipeline, final Function... pathFunctions) {
        super(pipeline);
        this.functionRing = new FunctionRing(pathFunctions);
        this.setFunction(holder -> {
            final Path path = new Path();
            holder.getPath().forEach((a, b) -> path.add(a, this.functionRing.next().apply(b)));
            return path;
        });
    }
}
