package com.alibaba.maxgraph.io;

import com.alibaba.maxgraph.compiler.custom.CustomPredicate;
import com.alibaba.maxgraph.compiler.custom.ListKeyPredicate;
import com.alibaba.maxgraph.compiler.custom.ListMatchType;
import com.alibaba.maxgraph.compiler.custom.ListPredicate;
import com.alibaba.maxgraph.compiler.custom.Lists;
import com.alibaba.maxgraph.compiler.custom.MatchType;
import com.alibaba.maxgraph.compiler.custom.NegatePredicate;
import com.alibaba.maxgraph.compiler.custom.PredicateType;
import com.alibaba.maxgraph.compiler.custom.RegexKeyPredicate;
import com.alibaba.maxgraph.compiler.custom.RegexPredicate;
import com.alibaba.maxgraph.compiler.custom.StringKeyPredicate;
import com.alibaba.maxgraph.compiler.custom.StringPredicate;
import com.alibaba.maxgraph.compiler.custom.Text;
import com.alibaba.maxgraph.compiler.custom.map.MapPropFillFunction;
import com.alibaba.maxgraph.compiler.custom.map.Prop;
import com.alibaba.maxgraph.compiler.custom.output.Output;
import com.alibaba.maxgraph.compiler.custom.output.OutputOdpsFunction;
import com.alibaba.maxgraph.compiler.custom.output.OutputOdpsTable;
import com.alibaba.maxgraph.compiler.custom.output.OutputTable;
import com.alibaba.maxgraph.compiler.custom.program.ConnectedComponentVertexProgram;
import com.alibaba.maxgraph.compiler.custom.program.CustomVertexProgram;
import com.alibaba.maxgraph.compiler.custom.program.Program;
import com.alibaba.maxgraph.graph.CompositeId;
import com.alibaba.maxgraph.graph.EntryValueResult;
import org.apache.tinkerpop.gremlin.structure.io.AbstractIoRegistry;
import org.apache.tinkerpop.gremlin.structure.io.gryo.GryoIo;

public class MaxGraphIORegistry extends AbstractIoRegistry {

    private static final MaxGraphIORegistry INSTANCE = new MaxGraphIORegistry();

    public MaxGraphIORegistry() {
        register(GryoIo.class, CompositeId.class, null);
        register(GryoIo.class, EntryValueResult.class, null);
        register(GryoIo.class, CustomPredicate.class, null);
        register(GryoIo.class, ListKeyPredicate.class, null);
        register(GryoIo.class, ListMatchType.class, null);
        register(GryoIo.class, ListPredicate.class, null);
        register(GryoIo.class, Lists.class, null);
        register(GryoIo.class, MatchType.class, null);
        register(GryoIo.class, NegatePredicate.class, null);
        register(GryoIo.class, PredicateType.class, null);
        register(GryoIo.class, RegexKeyPredicate.class, null);
        register(GryoIo.class, RegexPredicate.class, null);
        register(GryoIo.class, StringKeyPredicate.class, null);
        register(GryoIo.class, StringPredicate.class, null);
        register(GryoIo.class, Text.class, null);
        register(GryoIo.class, Output.class, null);
        register(GryoIo.class, OutputOdpsFunction.class, null);
        register(GryoIo.class, OutputOdpsTable.class, null);
        register(GryoIo.class, OutputTable.class, null);
        register(GryoIo.class, Prop.class, null);
        register(GryoIo.class, MapPropFillFunction.class, null);
        register(GryoIo.class, Program.class, null);
        register(GryoIo.class, ConnectedComponentVertexProgram.class, null);
        register(GryoIo.class, CustomVertexProgram.class, null);
    }

    public static MaxGraphIORegistry getInstance() {
        return INSTANCE;
    }

    public static MaxGraphIORegistry instance() {
        return INSTANCE;
    }
}
