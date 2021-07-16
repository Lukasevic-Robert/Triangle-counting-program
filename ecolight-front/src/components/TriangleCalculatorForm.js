import React, { useState, useEffect, useContext } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { FormControl, Grid, Button, Fab } from '@material-ui/core';
import AddIcon from '@material-ui/icons/Add';
import RemoveIcon from '@material-ui/icons/Remove';
import { ValidatorForm, TextValidator } from 'react-material-ui-form-validator';
import service from '../services/TriangleCalculatorService';
import Typography from '@material-ui/core/Typography';
import { CalculatorContext } from '../context/CalculatorContext';


const useStyles = makeStyles((theme) => ({
    container: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    textField: {
        margin: 10,
        width: 100,
    },
    formControl: {
        minWidth: 120,
    },
    submit: {
        marginLeft: 10
    },
    counter: {
        marginTop: 10,
        display: 'flex',
        justifyContent: 'flex-end',
        alignItems: 'flex-end',
        height: 50,
    },
}));

export default function TriangleCalculatorForm() {

    const classes = useStyles();
    const { setResponse } = useContext(CalculatorContext);
    const [inputList, setInputList] = useState([{ x: "", y: "" }, { x: "", y: "" }, { x: "", y: "" }]);
    const [request, setRequest] = useState({});
    const regex = '^[+-]?(([1-9][0-9]*)?[0-9](.[0-9]*)?|.[0-9]+)$';


    useEffect(() => {
        if (!isEmpty(request)) {
            getTriangleCount();
        }
    }, [request])

    const isEmpty = (obj) => {
        for (let prop in obj) {
            if (obj.hasOwnProperty(prop)) {
                return false;
            }
        }
        return JSON.stringify(obj) === JSON.stringify({});
    }

    const getTriangleCount = async () => {
        await service.getTriangleCount(request).then(response => {
            setResponse(response.data.numberOfTriangles);
        })
    }

    const handleFormSubmit = (event) => {
        event.preventDefault();
        let request = { coordinates: inputList };
        setRequest(request);
    }

    const handleChangeXYCoord = (index, event) => {
        const values = [...inputList];
        values[index][event.target.name] = event.target.value;
        setInputList(values);
    }

    const addCoords = () => {
        setInputList([...inputList, { x: "", y: "" }])
    }

    const removeCoords = () => {
        if (inputList.length > 3) {
            const values = [...inputList];
            values.pop();
            setInputList(values);
        }
    }

    const clearList = () => {
        const values = [...inputList];
        inputList.map((item, index) => {

            values[index].x = "";
            values[index].y = "";
        })
        setResponse(0)
        setInputList(values);
    }

    return (
        <div style={{ display: 'flex', justifyContent: 'center', flexDirection: 'row', marginLeft: 80 }}>
            <ValidatorForm id="rates-form" onSubmit={handleFormSubmit} >
                <Grid container justifyContent="center">
                    <FormControl>
                        {inputList.map((inputField, index) => (
                            <Grid key={index} container justifyContent="space-around">
                                <Typography className={classes.counter} style={{ color: 'black' }}>{index + 1 + `.`}</Typography>
                                <TextValidator
                                    id="x-coord"
                                    name="x"
                                    label="X"
                                    type="text"
                                    variant="filled"
                                    size="small"
                                    value={inputField.x}
                                    className={classes.textField}
                                    onChange={(event) => handleChangeXYCoord(index, event)}
                                    validators={['required', `matchRegexp:${regex}`]}
                                // errorMessages={['This field is required']}

                                />
                                <TextValidator
                                    id="y-coord"
                                    name="y"
                                    label="Y"
                                    type="text"
                                    variant="filled"
                                    size="small"
                                    value={inputField.y}
                                    className={classes.textField}
                                    onChange={(event) => handleChangeXYCoord(index, event)}
                                    validators={['required', `matchRegexp:${regex}`]}
                                // InputLabelProps={{
                                //     shrink: true,
                                // }}
                                />

                            </Grid>))}
                    </FormControl>
                </Grid>
                <FormControl style={{ display: 'flex', flexDirection: 'row', justifyContent: 'center', marginTop: 20 }}>
                    <Button id="clear" color="primary" variant="contained" onClick={() => clearList()} >Clear</Button>
                    <Button id="submit" color="secondary" className={classes.submit} variant="contained" type="submit">Submit</Button>
                </FormControl>
            </ValidatorForm>
            <div className="col">
                <Fab id="add-coord" onClick={() => addCoords()} color="primary" style={{ marginRight: 10 }} size="small" aria-label="add"><AddIcon /></Fab>
                <Fab id="remove-coord" onClick={() => removeCoords()} color="primary" size="small" aria-label="add"><RemoveIcon /></Fab>
            </div>
        </div>

    )
}
