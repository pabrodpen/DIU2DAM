import React, { useState } from 'react';
import '../styles/styles.css'; 

const FormularioComponent = ({artista,cancion,inputChange,buscar,actualizarLetra}) => {


    const [loading, setLoading] = useState(false);

    const onSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        setTimeout(async () => {
            await buscar();
            setLoading(false);
        }, 2000);
    };
    
    return (
        <form id='formulario' onSubmit={onSubmit}>
            <div>
                <label>Nombre de Artista:</label>
                <input type="text" id="artista" name="artista"
                onChange={(e) => inputChange("artista", e.target.value)}/>
            </div>
            <div>
                <label>Nombre de Canción:</label>
                <input type="text" id="cancion" name="cancion"
                onChange={(e) => inputChange("cancion", e.target.value)} />
            </div>
            <button type="submit">Buscar</button>
        </form>
    );
};

export default FormularioComponent;