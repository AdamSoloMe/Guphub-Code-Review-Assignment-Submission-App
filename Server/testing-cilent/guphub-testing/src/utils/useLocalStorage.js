import { useEffect,useState } from "react";


function useLocalState(defaultvalue,key){
   const[value,setValue] = useState(() => {
        const localStorageValue=localStorage.getItem(key);

        localStorageValue !== null 
        ? JSON.parse(localStorageValue) 
        : defaultvalue;
    });

    useEffect(() => {
        localStorage.setItem(key,JSON.stringify(value))

    },[key, value]);

    return [value,setValue];
}



export {useLocalState}