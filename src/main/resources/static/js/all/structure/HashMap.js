const MAP_PUT = 0,MAP_GET = 1,MAP_REMOVE = 2;
class HashMap{
    constructor(){
        this.size = 0;
        this.array = new Array(16);
        for(let i=0;i<this.array.length;i++) this.array[i] = [];
    }
    core(code,k,v){
        let hash = hashcode(k);
        hash = hash ^ (hash >>> 16);
        let tempArray = this.getArray(hash);
        let count = -1;
        for(let node of tempArray){
            count++;
            if(node.hash === hash && node.key === k){
                switch(code){
                    case MAP_PUT:
                        node.val = v;
                        return true;
                    case MAP_GET:
                        return node.val;
                    case MAP_REMOVE:
                        tempArray.splice(count,1);
                        this.size--;
                        node = null;
                        return true;
                }
            }
        }
        if(code === 0){
            tempArray.push(new Node(hash,k,v));
            this.size++;
        }
        return null;
    }
    put(k,v){
        if(this.core(MAP_PUT,k,v) && this.size > this.array.length * 0.75) this.expansion();
        return this;
    }
    get(k){
        return this.core(MAP_GET,k);
    }
    remove(k){
        return this.core(MAP_REMOVE,k);
    }
    clear(){
        for(let i=0;i<this.array.length;++i) this.array[i].splice(0,this.array[i].length);
        this.size = 0;
    }
    getArray(hash){
        if(hash < 0) hash = -hash;
        let index = (this.array.length-1) & hash;
        return this.array[index];
    }
    expansion(){
        for(let i=this.array.length,len=i<<1;i<len;++i) this.array[i] = [];
    }
}
class Node{
    constructor(hash,key,val){
        this.hash = hash;
        this.key = key;
        this.val = val;
    }
}