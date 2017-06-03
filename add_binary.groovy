public byte[] convert(final String str, final int padTo) {
    final byte[] ret = new byte[padTo];
    int byteIndex = 0;
    for(int i = str.length() - 1; i >= 0; --i) {
        final byte b = str.charAt(i) == '1' ? 1 : 0;
        ret[byteIndex++] = b;
    }

    return ret;
}

public String addBinary(final String a, final String b) {
    final int len = Math.max(a.length(), b.length()) + 1;
    final byte[] aBytes = convert(a, len);
    final byte[] bBytes = convert(b, len);

    int carry = 0;
    for(int i = 0; i < len; ++i) {
        aBytes[i] += (bBytes[i] + carry);
        if(aBytes[i] == 2) {
            carry = 1;
            aBytes[i] = 1;
        }
        else {
            carry = 0;
        }
    }
    
    final int startIndex = aBytes[aBytes.length - 1] == 0 ? aBytes.length - 2 : aBytes.length - 1;
    StringBuilder sb = new StringBuilder();
    for(int i = startIndex; i >= 0; --i) {
        sb.append(aBytes[i] == 1 ? '1' : '0');
    }

    return sb.toString();
}

println(addBinary("10000", "101"));
