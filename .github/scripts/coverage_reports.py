import csv
import os

def read_coverage_data(csv_path):
    coverage_data = []
    with open(csv_path, 'r') as file:
        csv_reader = csv.DictReader(file)
        for row in csv_reader:
            coverage_data.append(row)
    return coverage_data

def calculate_coverage_percentage(covered, missed):
    total = float(covered) + float(missed)
    if total == 0:
        return 0.0
    return (float(covered) / total) * 100

def generate_coverage_summary(coverage_data):
    summary = "## 📊 Code Coverage Report\n\n"
    summary += "| Package | Class | Instruction Coverage | Branch Coverage | Line Coverage |\n"
    summary += "|---------|-------|---------------------|-----------------|---------------|\n"

    total_instruction_covered = 0
    total_instruction_missed = 0
    total_branch_covered = 0
    total_branch_missed = 0
    total_line_covered = 0
    total_line_missed = 0

    for row in coverage_data:
        instruction_coverage = calculate_coverage_percentage(
            row['INSTRUCTION_COVERED'], row['INSTRUCTION_MISSED'])
        branch_coverage = calculate_coverage_percentage(
            row['BRANCH_COVERED'], row['BRANCH_MISSED'])
        line_coverage = calculate_coverage_percentage(
            row['LINE_COVERED'], row['LINE_MISSED'])

        total_instruction_covered += int(row['INSTRUCTION_COVERED'])
        total_instruction_missed += int(row['INSTRUCTION_MISSED'])
        total_branch_covered += int(row['BRANCH_COVERED'])
        total_branch_missed += int(row['BRANCH_MISSED'])
        total_line_covered += int(row['LINE_COVERED'])
        total_line_missed += int(row['LINE_MISSED'])

        summary += f"| {row['PACKAGE']} | {row['CLASS']} | {instruction_coverage:.2f}% | {branch_coverage:.2f}% | {line_coverage:.2f}% |\n"

    # Add total coverage
    total_instruction = calculate_coverage_percentage(
        total_instruction_covered, total_instruction_missed)
    total_branch = calculate_coverage_percentage(
        total_branch_covered, total_branch_missed)
    total_line = calculate_coverage_percentage(
        total_line_covered, total_line_missed)

    summary += "\n### 📈 Overall Coverage\n\n"
    summary += f"- **Instruction Coverage**: {total_instruction:.2f}%\n"
    summary += f"- **Branch Coverage**: {total_branch:.2f}%\n"
    summary += f"- **Line Coverage**: {total_line:.2f}%\n"

    return summary

def main():
    # Path to Jacoco CSV report
    csv_path = "target/site/jacoco/jacoco.csv"
    
    if not os.path.exists(csv_path):
        print(f"Error: Could not find Jacoco CSV report at {csv_path}")
        exit(1)

    coverage_data = read_coverage_data(csv_path)
    summary = generate_coverage_summary(coverage_data)
    
    # Write to GitHub Actions summary
    with open(os.environ.get('GITHUB_STEP_SUMMARY', 'coverage_summary.md'), 'w') as f:
        f.write(summary)

if __name__ == "__main__":
    main()