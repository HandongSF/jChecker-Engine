/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.isel.csee.jchecker2_0.diagram.parser.collector;

import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for collecting constructor information
 */
public class ConstructorCollector extends VoidVisitorAdapter<List<String>> {
    private List<String> constructorNameList = new ArrayList<>();
    private String constructorName;
    private String constructorModifier;
    private String constructorParamType;
    private String constructorInfo;

    /**
     * Visit method for searching and collecting constructor information
     * @param declaration ConstructorDeclaration
     * @param collector collector list
     */
    @Override
    public void visit(ConstructorDeclaration declaration, List<String> collector) {
        super.visit(declaration, collector);

        this.constructorName = declaration.getNameAsString();

        if (declaration.getModifiers().size() == 0) { this.constructorModifier = "package"; }
        else { this.constructorModifier = declaration.getModifiers().get(0).toString(); }

        if (declaration.getParameters().size() == 0) { this.constructorParamType = "()"; }
        else {
            if (declaration.getParameters().size() == 1) { this.constructorParamType = "(" + declaration.getParameter(0).getTypeAsString() + ")"; }
            else {
                for (int i = 0; i < declaration.getParameters().size(); i ++) {
                    if (i == 0) { this.constructorParamType = "(" + declaration.getParameter(i).getTypeAsString() + ", "; }
                    else if (i == (declaration.getParameters().size() - 1)) { this.constructorParamType += declaration.getParameter(i).getTypeAsString() + ")"; }
                    else { this.constructorParamType += declaration.getParameter(i).getTypeAsString() + ", "; }
                }
            }
        }

        if (this.constructorModifier.equals("private ")) { this.constructorInfo = "- " + this.constructorName + this.constructorParamType; }
        else if (this.constructorModifier.equals("public ")) { this.constructorInfo = "+ " + this.constructorName + this.constructorParamType; }
        else if (this.constructorModifier.equals("protected ")) { this.constructorInfo = "# " + this.constructorName + this.constructorParamType; }
        else { this.constructorInfo = "~ " + this.constructorName + this.constructorParamType; }

        // Add a constructor information in the collector
        collector.add(this.constructorInfo);
    }
}
