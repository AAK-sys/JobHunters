import React from "react";
import { Document, Page, Text, View, StyleSheet } from "@react-pdf/renderer";

function ResumeView({
    userInfo,
    selectSummaries,
    selectEducations,
    selectExperiences,
    selectSkills,
}) {
    return (
        <Document>
            <Page size="A4">
                <View>{userInfo && <div>{userInfo.email}</div>}</View>
            </Page>
        </Document>
    );
}

export default ResumeView;
