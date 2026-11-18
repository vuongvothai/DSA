import java.util.ArrayList;
import java.util.List;

public class IntergalacticTransmission {

    public static List<Integer> getTransmitSequence(List<Integer> message) {

        List<Integer> result = new ArrayList<>();

        if(message.isEmpty()){
            return result;
        }

        StringBuilder bits = new StringBuilder();
        
        for (int b: message){
            bits.append(String.format("%8s",Integer.toBinaryString( b & 0xFF))
                        .replace(' ', '0'));
        }
        for (int i = 0; i < bits.length(); i += 7){
            String chunk = bits.substring(i, Math.min(i+7, bits.length()));

            while (chunk.length() < 7){
                chunk += "0";
            }

            int data = Integer.parseInt(chunk, 2);

            int parity = Integer.bitCount(data) % 2;

            int transmitted = (data << 1) | parity;

            result.add(transmitted);
        }
        return result;
    }
 
    public static List<Integer> decodeSequence(List<Integer> sequence) {
        List<Integer> result = new ArrayList<>();

        if (sequence.isEmpty()){
            return result;
        }
        StringBuilder bits = new StringBuilder();

        for (int transmitted : sequence){
            
            if(Integer.bitCount(transmitted)%2 != 0) {
                throw new IllegalArgumentException();
            }
            
            int data = transmitted >>> 1;

            bits.append(String.format("%7s",Integer.toBinaryString(data))
                        .replace(' ', '0'));
        }

        for (int i = 0; i + 8 <= bits.length(); i += 8) {
            
            String byteBits = bits.substring(i, i + 8);
            
            result.add(Integer.parseInt(byteBits, 2));
        }

        return result;
    }

}
