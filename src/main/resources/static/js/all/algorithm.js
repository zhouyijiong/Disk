function md5(string){function md5_RotateLeft(lValue,iShiftBits){return(lValue<<iShiftBits)|(lValue>>>(32-iShiftBits))}function md5_AddUnsigned(lX,lY){let lX4,lY4,lX8,lY8,lResult;lX8=(lX&2147483648);lY8=(lY&2147483648);lX4=(lX&1073741824);lY4=(lY&1073741824);lResult=(lX&1073741823)+(lY&1073741823);if(lX4&lY4){return(lResult^2147483648^lX8^lY8)}if(lX4|lY4){if(lResult&1073741824){return(lResult^3221225472^lX8^lY8)}else{return(lResult^1073741824^lX8^lY8)}}else{return(lResult^lX8^lY8)}}function md5_F(x,y,z){return(x&y)|((~x)&z)}function md5_G(x,y,z){return(x&z)|(y&(~z))}function md5_H(x,y,z){return(x^y^z)}function md5_I(x,y,z){return(y^(x|(~z)))}function md5_FF(a,b,c,d,x,s,ac){a=md5_AddUnsigned(a,md5_AddUnsigned(md5_AddUnsigned(md5_F(b,c,d),x),ac));return md5_AddUnsigned(md5_RotateLeft(a,s),b)}function md5_GG(a,b,c,d,x,s,ac){a=md5_AddUnsigned(a,md5_AddUnsigned(md5_AddUnsigned(md5_G(b,c,d),x),ac));return md5_AddUnsigned(md5_RotateLeft(a,s),b)}function md5_HH(a,b,c,d,x,s,ac){a=md5_AddUnsigned(a,md5_AddUnsigned(md5_AddUnsigned(md5_H(b,c,d),x),ac));return md5_AddUnsigned(md5_RotateLeft(a,s),b)}function md5_II(a,b,c,d,x,s,ac){a=md5_AddUnsigned(a,md5_AddUnsigned(md5_AddUnsigned(md5_I(b,c,d),x),ac));return md5_AddUnsigned(md5_RotateLeft(a,s),b)}function md5_ConvertToWordArray(string){let lWordCount;let lMessageLength=string.length;let lNumberOfWords_temp1=lMessageLength+8;let lNumberOfWords_temp2=(lNumberOfWords_temp1-(lNumberOfWords_temp1%64))/64;let lNumberOfWords=(lNumberOfWords_temp2+1)*16;let lWordArray=Array(lNumberOfWords-1);let lBytePosition=0;let lByteCount=0;while(lByteCount<lMessageLength){lWordCount=(lByteCount-(lByteCount%4))/4;lBytePosition=(lByteCount%4)*8;lWordArray[lWordCount]=(lWordArray[lWordCount]|(string.charCodeAt(lByteCount)<<lBytePosition));lByteCount++}lWordCount=(lByteCount-(lByteCount%4))/4;lBytePosition=(lByteCount%4)*8;lWordArray[lWordCount]=lWordArray[lWordCount]|(128<<lBytePosition);lWordArray[lNumberOfWords-2]=lMessageLength<<3;lWordArray[lNumberOfWords-1]=lMessageLength>>>29;
    return lWordArray}function md5_WordToHex(lValue){let WordToHexValue="",WordToHexValue_temp="",lByte,lCount;for(lCount=0;lCount<=3;lCount++){lByte=(lValue>>>(lCount*8))&255;WordToHexValue_temp="0"+lByte.toString(16);WordToHexValue=WordToHexValue+WordToHexValue_temp.substr(WordToHexValue_temp.length-2,2)}return WordToHexValue}function md5_Utf8Encode(string){string=string.replace(/\r\n/g,"\n");let utfText="";for(let n=0;n<string.length;n++){let c=string.charCodeAt(n);if(c<128){utfText+=String.fromCharCode(c)}else{if((c>127)&&(c<2048)){utfText+=String.fromCharCode((c>>6)|192);utfText+=String.fromCharCode((c&63)|128)}else{utfText+=String.fromCharCode((c>>12)|224);utfText+=String.fromCharCode(((c>>6)&63)|128);utfText+=String.fromCharCode((c&63)|128)}}}return utfText}let x;let k,AA,BB,CC,DD,a,b,c,d;let S11=7,S12=12,S13=17,S14=22;let S21=5,S22=9,S23=14,S24=20;let S31=4,S32=11,S33=16,S34=23;let S41=6,S42=10,S43=15,S44=21;string=md5_Utf8Encode(string);x=md5_ConvertToWordArray(string);a=1732584193;b=4023233417;c=2562383102;d=271733878;for(k=0; k<x.length; k+=16){AA=a;BB=b;CC=c;DD=d;a=md5_FF(a,b,c,d,x[k],S11,3614090360);d=md5_FF(d,a,b,c,x[k+1],S12,3905402710);c=md5_FF(c,d,a,b,x[k+2],S13,606105819);b=md5_FF(b,c,d,a,x[k+3],S14,3250441966);a=md5_FF(a,b,c,d,x[k+4],S11,4118548399);d=md5_FF(d,a,b,c,x[k+5],S12,1200080426);c=md5_FF(c,d,a,b,x[k+6],S13,2821735955);b=md5_FF(b,c,d,a,x[k+7],S14,4249261313);a=md5_FF(a,b,c,d,x[k+8],S11,1770035416);d=md5_FF(d,a,b,c,x[k+9],S12,2336552879);c=md5_FF(c,d,a,b,x[k+10],S13,4294925233);b=md5_FF(b,c,d,a,x[k+11],S14,2304563134);a=md5_FF(a,b,c,d,x[k+12],S11,1804603682);d=md5_FF(d,a,b,c,x[k+13],S12,4254626195);c=md5_FF(c,d,a,b,x[k+14],S13,2792965006);b=md5_FF(b,c,d,a,x[k+15],S14,1236535329);a=md5_GG(a,b,c,d,x[k+1],S21,4129170786);d=md5_GG(d,a,b,c,x[k+6],S22,3225465664);c=md5_GG(c,d,a,b,x[k+11],S23,643717713);b=md5_GG(b,c,d,a,x[k],S24,3921069994);a=md5_GG(a,b,c,d,x[k+5],S21,3593408605);d=md5_GG(d,a,b,c,x[k+10],S22,38016083);c=md5_GG(c,d,a,b,x[k+15],S23,3634488961);
    b=md5_GG(b,c,d,a,x[k+4],S24,3889429448);a=md5_GG(a,b,c,d,x[k+9],S21,568446438);d=md5_GG(d,a,b,c,x[k+14],S22,3275163606);c=md5_GG(c,d,a,b,x[k+3],S23,4107603335);b=md5_GG(b,c,d,a,x[k+8],S24,1163531501);a=md5_GG(a,b,c,d,x[k+13],S21,2850285829);d=md5_GG(d,a,b,c,x[k+2],S22,4243563512);c=md5_GG(c,d,a,b,x[k+7],S23,1735328473);b=md5_GG(b,c,d,a,x[k+12],S24,2368359562);a=md5_HH(a,b,c,d,x[k+5],S31,4294588738);d=md5_HH(d,a,b,c,x[k+8],S32,2272392833);c=md5_HH(c,d,a,b,x[k+11],S33,1839030562);b=md5_HH(b,c,d,a,x[k+14],S34,4259657740);a=md5_HH(a,b,c,d,x[k+1],S31,2763975236);d=md5_HH(d,a,b,c,x[k+4],S32,1272893353);c=md5_HH(c,d,a,b,x[k+7],S33,4139469664);b=md5_HH(b,c,d,a,x[k+10],S34,3200236656);a=md5_HH(a,b,c,d,x[k+13],S31,681279174);d=md5_HH(d,a,b,c,x[k],S32,3936430074);c=md5_HH(c,d,a,b,x[k+3],S33,3572445317);b=md5_HH(b,c,d,a,x[k+6],S34,76029189);a=md5_HH(a,b,c,d,x[k+9],S31,3654602809);d=md5_HH(d,a,b,c,x[k+12],S32,3873151461);c=md5_HH(c,d,a,b,x[k+15],S33,530742520);b=md5_HH(b,c,d,a,x[k+2],S34,3299628645);a=md5_II(a,b,c,d,x[k],S41,4096336452);d=md5_II(d,a,b,c,x[k+7],S42,1126891415);c=md5_II(c,d,a,b,x[k+14],S43,2878612391);b=md5_II(b,c,d,a,x[k+5],S44,4237533241);a=md5_II(a,b,c,d,x[k+12],S41,1700485571);d=md5_II(d,a,b,c,x[k+3],S42,2399980690);c=md5_II(c,d,a,b,x[k+10],S43,4293915773);b=md5_II(b,c,d,a,x[k+1],S44,2240044497);a=md5_II(a,b,c,d,x[k+8],S41,1873313359);d=md5_II(d,a,b,c,x[k+15],S42,4264355552);c=md5_II(c,d,a,b,x[k+6],S43,2734768916);b=md5_II(b,c,d,a,x[k+13],S44,1309151649);a=md5_II(a,b,c,d,x[k+4],S41,4149444226);d=md5_II(d,a,b,c,x[k+11],S42,3174756917);c=md5_II(c,d,a,b,x[k+2],S43,718787259);b=md5_II(b,c,d,a,x[k+9],S44,3951481745);a=md5_AddUnsigned(a,AA);b=md5_AddUnsigned(b,BB);c=md5_AddUnsigned(c,CC);d=md5_AddUnsigned(d,DD)}return(md5_WordToHex(a)+md5_WordToHex(b)+md5_WordToHex(c)+md5_WordToHex(d)).toLowerCase()}
function getBytes(str){
    let bytes = [];
    let len = str.length;
    for(let i=0;i<len;++i){
        let c = str.charCodeAt(i);
        if(c>=0x010000 && c<=0x10FFFF){
            bytes.push(((c>>18)&0x07)|0xF0);
            bytes.push(((c>>12)&0x3F)|0x80);
            bytes.push(((c>>6)&0x3F)|0x80);
            bytes.push((c&0x3F)|0x80);
        }else if(c>=0x000800&&c<=0x00FFFF){
            bytes.push(((c>>12)&0x0F)|0xE0);
            bytes.push(((c>>6)&0x3F)|0x80);
            bytes.push((c&0x3F)|0x80);
        }else if(c>=0x000080&&c<=0x0007FF){
            bytes.push(((c>>6)&0x1F)|0xC0);
            bytes.push((c&0x3F)|0x80);
        }else{
            bytes.push(c&0xFF);
        }
    }
    return new Int8Array(bytes);
}
function byteToBigInt(bytes){
    bytes = bytes.reverse();
    let result = 0n;
    for(let i=0,len=bytes.length;i<len;++i){
        result |= BigInt(bytes[i] & 0xff) << BigInt(i*8);
    }
    return result;
}
function fastModPow(g,x,p){
    let r=1n,c=g%p;
    while(x>0){
        if(x%2n===1n) r=r*c%p;
        x=x/2n;
        c=(c*c)%p;
    }
    return r;
}
function rsa(str){
    let bytes = getBytes(str);
    let num = byteToBigInt(bytes);
    return fastModPow(num,65537n,BigInt(sessionStorage.product)).toString();
    // sessionStorage.only
    // BigInteger bigNum = new BigInteger(info.getBytes(StandardCharsets.UTF_8)).modPow(num, product);
    // byte[] bytes = bigNum.toByteArray();
    // return new String(Codec.simple(bytes, PrivateKey.OFFSET), 0, bytes.length, StandardCharsets.UTF_8);
}
const CHARS='ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/';
function base64_encode(bytes){
    if(bytes==null||bytes.length===0) return [];
    let len=bytes.length;
    let lens=Math.floor(len/3)*3;
    let cnt=((len-1)/3+1)<<2;
    let dest = [];
    for(let s=0,d=0;s<lens;){
        let i=(bytes[s++]&0xff)<<16|(bytes[s++]&0xff)<<8|(bytes[s++]&0xff);
        dest[d++]=getBytes(CHARS[(i>>>18)&0x3f]);
        dest[d++]=getBytes(CHARS[(i>>>12)&0x3f]);
        dest[d++]=getBytes(CHARS[(i>>>6)&0x3f]);
        dest[d++]=getBytes(CHARS[i&0x3f]);
    }
    let left=len-lens;
    let tag = getBytes('=');
    if(left>0){
        let i=((bytes[lens]&0xff)<<10)|(left===2?((bytes[len-1]&0xff)<<2):0);
        dest[cnt-4]=getBytes(CHARS[i>>12]);
        dest[cnt-3]=getBytes(CHARS[(i>>>6)&0x3f]);
        dest[cnt-2]=left===2?getBytes(CHARS[i&0x3f]):tag;
        dest[cnt-1]=tag;
    }
    let result = [];
    dest.forEach(item=>{
        result.push(String.fromCharCode(item));
    });
    return result.join('');
}
// function getBytesCore(chars,index,len){
//     let var6 = 0;
//     let var4 = [len * 3];
//     for(let var7=var6+len;var6<var7&&chars[index]<128;++var6) var4[var6]=chars[index++];
//     while(index<index+len){
//         let var8 = chars[index++];
//         if(var8<128){
//             var4[var6++]=var8;
//         }else if(var8<2048){
//             var4[var6++]=(192 | var8 >> 6);
//             var4[var6++]=(128 | var8 & 63);
//         }else {
//             var4[var6++] = (224 | var8 >> 12);
//             var4[var6++] = (128 | var8 >> 6 & 63);
//             var4[var6++] = (128 | var8 & 63);
//         }
//     }
//     return var6;
// }