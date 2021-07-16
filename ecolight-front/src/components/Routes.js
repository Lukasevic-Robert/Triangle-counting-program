import React from 'react';
import { Switch, Route } from "react-router-dom";
import TriangleCalculator from './TriangleCalculator';

export default function Routes() {
    return (
        <Switch>
            <Route exact path="/" component={TriangleCalculator} />
            <Route path="/triangle-counting-with-coords" component={TriangleCalculator} />
        </Switch>
    )
}
