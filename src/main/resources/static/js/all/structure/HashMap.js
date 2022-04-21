/** 期望超过8个节点转为二叉树 */
const DEFAULT_LOAD_FACTOR = 0.75;
class HashMap{
    constructor(size){
        this.size = 0;
        this.array = new Array(size ? size : 16).fill(null);
        this.threshold = this.array.length * DEFAULT_LOAD_FACTOR;
    }
    put(k,v){
        let hash = this.hash(k);
        let index = this.getIndex(this.array.length,hash);
        let node = this.array[index];
        if(node == null) this.array[index] = new Node(hash,k,v);
        else{
            let parentNode = null;
            do{
                if(node.hash === hash && node.key === k){
                    node.val = v;
                    return this;
                }
                parentNode = node;
                node = node.next;
            }while(node != null);
            parentNode.next = new Node(hash,k,v);
        }
        if(++this.size > this.threshold) this.expansion();
        return this;
    }
    get(k){
        let hash = this.hash(k);
        let index = this.getIndex(this.array.length,hash);
        let node = this.array[index];
        while(node != null){
            if(node.hash === hash && node.key === k) return node.val;
            node = node.next;
        }
        return null;
    }
    remove(k){
        let hash = this.hash(k);
        let index = this.getIndex(this.array.length,hash);
        let node = this.array[index];
        if(node == null) return null;
        let parentNode = null;
        do{
            if(node.hash === hash && node.key === k){
                let val = node.val;
                if(parentNode == null){
                    this.array[index] = null;
                }else{
                    parentNode.next = node.next;
                    node = null;
                }
                --this.size;
                return val;
            }
            parentNode = node;
            node = node.next;
        }while(node != null);
        return null;
    }
    clear(){
        for(let i=0,len=this.array.length;i<len;++i) this.array[i] = null;
        this.size = 0;
    }
    hash(k){
        let hash = hashcode(k);
        return hash ^ (hash >>> 16);
    }
    getIndex(len,hash){
        return (len - 1) & hash
    }
    expansion(){
        this.threshold = (this.array.length << 1) * DEFAULT_LOAD_FACTOR;
        let newMap = new HashMap(this.array.length << 1);
        let node = null,next = null;
        for(let i=0,len=this.array.length;i<len;++i){
            node = this.array[i];
            while(node != null){
                newMap.put(node.key,node.val);
                next = node.next;
                while(next != null){
                    newMap.put(next.key,next.val);
                    next = next.next;
                }
                node = null;
            }
        }
        this.array = newMap.array;
        newMap = null;
    }
}
class Node{
    constructor(hash,key,val){
        this.hash = hash;
        this.key = key;
        this.val = val;
        this.next = null;
    }
}