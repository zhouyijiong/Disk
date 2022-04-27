/**
 * @Author: ZYJ
 * @Date: 2022/04/022
 * @Remark: 父类 Parent
 */
class Parent{
    constructor(type){
        this.type = typeof type;
        if(type) this.check = true;
    }
    hashcode(str){
        let hash = 0,i,chr,len;
        if(str.length === 0) return hash;
        for(i=0,len=str.length;i<len;i++){
            chr = str.charCodeAt(i);
            hash = ((hash << 5) - hash) + chr;
            hash |= 0;
        }
        return hash;
    }
    param_check(param){if(this.check && (typeof param !== this.type)) throw 'parameter must be ' + type;}
    toString(){return null;}
}

/**
 * @Author: ZYJ
 * @Date: 2022/04/022
 * @Remark: 数据结构 HashMap TODO:期望超过8个节点转为二叉树
 */
const DEFAULT_LOAD_FACTOR = 0.75;
class HashMap extends Parent{
    constructor(size){
        super('string');
        this.size = 0;
        this.array = new Array(size ? size : 16).fill(null);
        this.threshold = this.array.length * DEFAULT_LOAD_FACTOR;
    }
    put(k,v){
        let hash = this.hash(k);
        let index = this.getIndex(hash);
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
        let index = this.getIndex(hash);
        let node = this.array[index];
        while(node != null){
            if(node.hash === hash && node.key === k) return node.val;
            node = node.next;
        }
        return null;
    }
    remove(k){
        let hash = this.hash(k);
        let index = this.getIndex(hash);
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
        super.param_check(k);
        let hash = super.hashcode(k);
        return hash ^ (hash >>> 16);
    }
    getIndex(hash){return (this.array.length - 1) & hash}
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

/**
 * @Author: ZYJ
 * @Date: 2022/04/25
 * @Remark: K-Value
 */
class Pair extends Parent{
    constructor(key,val){
        super();
        this.key = new Item(key);
        this.val = new Item(val);
    }
    toString(){return '{' + this.key.toString() + ':' + this.val.toString() + '}';}
    hashcode(){return super.hashcode(this.toString());}
}
class Item extends Object{
    constructor(item){
        super(item);
        this.item = item;
    }
    set(item){
        super.param_check(item);
        this.item = item;
    }
    get(){return this.item;}
    setType(type){super.type = type;}
    toString(){return String(this.item);}
}

/**
 * @Author: ZYJ
 * @Date: 2022/04/022
 * @Remark: Ajax
 */
class Ajax{
    constructor(){}
    get(url,formData){return this.send(GET,url,'json',formData);}
    post(url,formData){return this.send(POST,url,'json',formData)};
    send(type,url,dataType,formData){
        let result = false;
        alert('准备');
        $.ajax({
            type:type,
            url:url,
            dataType:dataType,
            contentType:false,
            processData:false,
            data:formData,
            async:false,
            beforeSend:function(XMLHttpRequest){
                XMLHttpRequest.setRequestHeader('token',localStorage.getItem('token'));
            },
            success:function(data){
                if(data.code >= 4000){
                    result = true;
                    alert(data.msg);
                }
        	}
        });
        return result;
    }
    syncGet(url){
        let xmlHttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
        xmlHttp.onreadystatechange = function(){
            if(this.readyState === 4 && xmlHttp.status === 200){
                location.reload();
            }
        }
        xmlHttp.open(GET,url,false);
        xmlHttp.setRequestHeader('token','aaaa');
        xmlHttp.send(null);
    }
}