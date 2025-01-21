import React from 'react';

const FormularioComponent = () => {
    return (
        <form>
            <div>
                <label>Nombre de Artista:</label>
                <input type="text" id="artista" name="artista" />
            </div>
            <div>
                <label>Nombre de Canción:</label>
                <input type="text" id="cancion" name="cancion" />
            </div>
            <button type="submit">Buscar</button>
        </form>
    );
};

export default FormularioComponent;