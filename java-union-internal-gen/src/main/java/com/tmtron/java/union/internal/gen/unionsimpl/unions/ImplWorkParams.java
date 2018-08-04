package com.tmtron.java.union.internal.gen.unionsimpl.unions;

class ImplWorkParams {
    final int unionIndex;
    final int implementationIndex;

    /**
     * Eg. for Union4Imp3, unionIndex=4 and implementationIndex=2
     */
    public ImplWorkParams(final int unionIndex, final int implementationIndex) {
        this.unionIndex = unionIndex;
        this.implementationIndex = implementationIndex;
    }
}
