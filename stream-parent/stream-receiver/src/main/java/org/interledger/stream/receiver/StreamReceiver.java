package org.interledger.stream.receiver;

import org.interledger.core.InterledgerAddress;
import org.interledger.core.InterledgerPreparePacket;
import org.interledger.core.InterledgerResponsePacket;
import org.interledger.spsp.StreamConnectionDetails;
import org.interledger.stream.Denomination;

/**
 * <p>A service that fulfills incoming STREAM packets.</p>
 */
public interface StreamReceiver {

  /**
   * <p>Setup a stream with another participant.</p>
   *
   * <p>A server MUST communicate the following values to a client using an authenticated, encrypted communication
   * channel (such as HTTPS). Key exchange is NOT provided by STREAM.</p>
   *
   * @param receiverAddress The {@link InterledgerAddress} of the receiver.
   *
   * @return A {@link StreamConnectionDetails} that is uniquely generated on every request.
   */
  StreamConnectionDetails setupStream(InterledgerAddress receiverAddress);

  /**
   * Receive money on behalf of {@code clientAddress} using the STREAM protocol.
   *
   * <p>Note that, per https://github.com/hyperledger/quilt/issues/242, as of the publication of this client,
   * connectors will reject ILP packets that exceed 32kb (though there is no hard rule that more than 32kb will not be
   * supported in the future.</p>
   *
   * @param preparePacket The actual {@link InterledgerPreparePacket} with a destination that includes information that
   *                      could only have been created by this receiver.
   * @param clientAddress A {@link InterledgerAddress} of the account this packet should be delivered to.
   * @param denomination  A {@link Denomination} containing information about the value this receiever is receiving.
   *
   * @return An {@link InterledgerResponsePacket} containing the ILPv4 response that should be returned for this call.
   */
  InterledgerResponsePacket receiveMoney(
      InterledgerPreparePacket preparePacket, InterledgerAddress clientAddress, Denomination denomination
  );

}
