package com.github.glusk.srp6_variables;

import java.nio.ByteOrder;

import com.github.glusk.caesar.Bytes;

/**
 * This class provides a skeletal implementation of the
 * {@link SRP6IntegerVariable} interface to minimize the effort required to
 * implement this interface.
 * <p>
 * Method {@code equals()} and {@code hashCode()} are already implemented as
 * per the specification in {@link SRP6IntegerVariable}.
 */
public abstract class AbstractSRP6IntegerVariable
    implements SRP6IntegerVariable {
    /**
     * {@inheritDoc}
     * <p>
     * <strong>Implementation Notes:</strong><br>
     * This implementation first checks if the specified object is this
     * SRP-6 Integer Variable. If so, it returns {@code true}; if not, it
     * checks if the specified object is an SRP-6 Integer Variable. If not, it
     * returns {@code false}; if so, it checks whether both
     * SRP-6 Integer Variables map to the same byte sequence; if so, it returns
     * {@code true}.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SRP6IntegerVariable)) {
            return false;
        }
        SRP6IntegerVariable that = (SRP6IntegerVariable) obj;
        Bytes b0 = this.bytes(ByteOrder.BIG_ENDIAN);
        Bytes b1 = that.bytes(ByteOrder.BIG_ENDIAN);
        return b0.equals(b1);
    }
}
