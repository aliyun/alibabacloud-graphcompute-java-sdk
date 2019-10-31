package com.alibaba.maxgraph.compiler.custom.map;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.tinkerpop.gremlin.process.traversal.Traverser;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkArgument;

public class MapPropFillFunction<VALUE> implements Function<Traverser<VALUE>, Traverser<VALUE>>, Serializable {
    private static final long serialVersionUID = -26705062931264590L;

    private List<String> propNameList;

    public MapPropFillFunction() {

    }
    
    public MapPropFillFunction(String[] propNameList) {
        checkArgument(null != propNameList && propNameList.length > 0, "fill prop list can't be empty");
        this.propNameList = Lists.newArrayList(propNameList);
    }

    @Override
    public Traverser<VALUE> apply(Traverser<VALUE> valueTraverser) {
        throw new NotImplementedException("apply");
    }

    public List<String> getPropNameList() {
        return propNameList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapPropFillFunction<?> that = (MapPropFillFunction<?>) o;
        return Objects.equal(propNameList, that.propNameList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(propNameList);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("propNameList", propNameList)
                .toString();
    }
}
