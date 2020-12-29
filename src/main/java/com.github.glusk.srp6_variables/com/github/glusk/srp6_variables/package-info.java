/**
 * A collection of SRP-6 Variables that can be used to implement
 * the SRP-6 protocol.
 * <p>
 * Here is a full list:
 * <pre>
 * N    A large safe prime (N = 2q+1, where q is prime)
 *      All arithmetic is done modulo N.
 * g    A generator modulo N
 * k    Multiplier parameter (k = H(N, g) in SRP-6a, k = 3 for legacy SRP-6)
 * s    User's salt
 * u    Random scrambling parameter
 * a,b  Secret ephemeral values
 * A,B  Public ephemeral values
 * x    Private key derived from the password and salt
 * v    Password verifier
 * ---
 * M1,M2  Proofs of session key
 * S      Session key
 * K      Hashed session key
 * </pre>
 * <p>
 * We can roughly divide the variables into 2 groups:
 * <ul>
 *   <li>{@link SRP6IntegerVariable}s</li>
 *   <li>plain {@link com.github.glusk.caesar.Bytes}</li>
 * </ul>.
 */
package com.github.glusk.srp6_variables;
