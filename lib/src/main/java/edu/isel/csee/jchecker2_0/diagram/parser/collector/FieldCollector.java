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

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for collecting field information
 */
public class FieldCollector extends VoidVisitorAdapter<List<String>> {
    private List<String> fieldNameList = new ArrayList<>();
    private String fieldName;
    private String fieldModifier;
    private String fieldType;
    private String fieldInfo;

    /**
     * Visit method for searching and collecting field information
     * @param declaration FieldDeclaration
     * @param collector collector list
     */
    @Override
    public void visit(FieldDeclaration declaration, List<String> collector) {
        super.visit(declaration, collector);

        // Add a field name in the collector
        this.fieldName = declaration.getVariable(0).getNameAsString();

        // - filedName: fieldType
        if (declaration.getModifiers().size() == 0) { this.fieldModifier = "package"; }
        else { this.fieldModifier = declaration.getModifiers().get(0).toString(); }

        this.fieldType = declaration.getVariable(0).getTypeAsString();

        if (this.fieldModifier.equals("private ")) { this.fieldInfo = "- " + this.fieldName + ": " + this.fieldType; }
        else if (this.fieldModifier.equals("public ")) { this.fieldInfo = "+ " + this.fieldName + ": " + this.fieldType; }
        else if (this.fieldModifier.equals("protected ")) { this.fieldInfo = "# " + this.fieldName + ": " + this.fieldType; }
        else { this.fieldInfo = "~ " + this.fieldName + ": " + this.fieldType; }

        // Add a field information in the collector
        collector.add(this.fieldInfo);
    }

    /**
     * Method for setting field name list
     * @param fieldNameList field name list
     */
    public void setFieldNameList(List<String> fieldNameList) { this.fieldNameList = fieldNameList; }

    /**
     * Method for return field name list
     * @return fieldNameList
     */
    public List<String> getFieldNameList() { return this.fieldNameList; }
}
