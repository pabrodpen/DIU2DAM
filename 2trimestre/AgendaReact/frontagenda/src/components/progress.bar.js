import React from "react";

const ProgressBar = ({ totalPersons, maxPersons }) => {
  const progress = (totalPersons / maxPersons) * 100;

  return (
    <div className="progress-container">
      <label><strong>Capacidad de contactos:</strong> {totalPersons}/{maxPersons}</label>
      <div className="progress-bar">
        <div
          className="progress-fill"
          style={{
            width: `${progress}%`,
            backgroundColor: totalPersons >= maxPersons ? "red" : "green",
          }}
        ></div>
      </div>
      {totalPersons >= maxPersons && (
        <p className="warning">⚠️ Has alcanzado el límite de contactos.</p>
      )}
    </div>
  );
};

export default ProgressBar;
